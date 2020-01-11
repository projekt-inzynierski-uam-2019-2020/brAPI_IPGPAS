import csv
import json
import os
import zipfile

import pprint


def extract_zips():
    zips_in_directory = [x for x in os.listdir() if zipfile.is_zipfile(x)]

    for zip_ in zips_in_directory:
        with zipfile.ZipFile(zip_) as item:
            item.extractall()


def convert():
    root_dir = os.getcwd()
    directories = [x for x in os.listdir() if os.path.isdir(x)]
    files = []

    for dir_ in directories:
        os.chdir(dir_)

        isatab_files = os.listdir()
        isatab_files_dict = {}


        isatab_files_dict['i_files'] = [
            x for x in isatab_files if x.startswith('i_')
        ]

        isatab_files_dict['s_files'] = [
            x for x in isatab_files if x.startswith('s_')
        ]

        isatab_files_dict['a_files'] = [
            x for x in isatab_files if x.startswith('a_')
        ]

        isatab_files_dict['d_files'] = [
            x for x in isatab_files if x.startswith('d_') or x.startswith('data_') or x.startswith('ghouse')
        ]

        isatab_json = {
            'Investigation Identifier': '',
            'Investigation Title': '',
            'Study Title': '',
            'Crop': [],
            'Samples': {},
            'Assays': {},
            'Data': {}
        }

        with open(isatab_files_dict['i_files'][0], encoding='utf8') as i_file:
            for line in i_file:
                if 'Investigation Identifier' in line:
                    isatab_json['Investigation Identifier'] = line.replace('Investigation Identifier', '').strip()
                elif 'Investigation Title' in line:
                    isatab_json['Investigation Title'] = line.replace('Investigation Title', '').strip()
                elif 'Study Title' in line:
                    isatab_json['Study Title'] = line.replace('Study Title', '').strip()
                else:
                    continue

        for s_file in isatab_files_dict['s_files']:

            file_name = s_file

            with open(s_file, encoding='utf-8-sig') as tsv_file:
                reader = csv.DictReader(tsv_file, dialect='excel-tab')
                try:

                    for row in reader:

                        organism = row['Characteristics[Organism]']
                        germplasm = row['Characteristics[Infraspecific name]']
                        sample_name = row['Sample Name']

                        isatab_json['Crop'].append(organism)
                        isatab_json['Crop'] = list(set(isatab_json['Crop']))

                        if file_name not in isatab_json['Samples']:
                            isatab_json['Samples'][file_name] = {}

                        if germplasm not in isatab_json['Samples'][file_name]:
                            isatab_json['Samples'][file_name][germplasm] = []

                        isatab_json['Samples'][file_name][germplasm].append(sample_name)
                        # isatab_json['Samples'][file_name][germplasm] = list(set(isatab_json['Samples'][germplasm][file_name]))
                
                except KeyError as key_error:
                    
                    # print(f'KeyError in the following file: {s_file}, {key_error}')
                    break

        for a_file in isatab_files_dict['a_files']:

            file_name = a_file

            with open(a_file, encoding='utf-8-sig') as tsv_file:
                reader = csv.DictReader(tsv_file, dialect='excel-tab')
                try:

                    for row in reader:

                        data_file = row['Derived Data File']
                        sample_name  = row['Sample Name']
                        assay_name = row['Assay Name']

                        if data_file not in isatab_json['Assays']:
                            isatab_json['Assays'][data_file] = {}

                        if sample_name not in isatab_json['Assays'][data_file]:
                            isatab_json['Assays'][data_file][sample_name] = ''

                        isatab_json['Assays'][data_file][sample_name] = assay_name
                            
                except KeyError as key_error:
                    # print(f'KeyError in the following file: {a_file}, {key_error}')
                    break

        for d_file in isatab_files_dict['d_files']:

            file_name = d_file



            with open(d_file, encoding='utf-8-sig') as tsv_file:
               
                reader = csv.DictReader(tsv_file, dialect='excel-tab')
                
                headers = reader.fieldnames.copy()
                headers.pop(0)
                headers = [x for x in headers if x != '']
                                
                try:
                    
                    assays = isatab_json['Assays'][file_name]   

                    for row in reader:

                        if row['Assay Name']:
                            assay_name = row['Assay Name']
                        elif row['Assay name']:
                            assay_name = row['Assay name']


                        if file_name not in isatab_json['Data']:
                            isatab_json['Data'][file_name] = {}

                        if assay_name not in isatab_json['Data'][file_name]:
                            isatab_json['Data'][file_name][assay_name] = {}

                        for key, value in assays.items():
                            if isatab_json['Data'][file_name][assay_name] == value:
                                print(True)
                            # print(key, value)

                        for header in headers:
                            isatab_json['Data'][file_name][assay_name][header] = row[header].replace(',', '.')


                except KeyError as key_error:
                    print(f'KeyError in the following file: {file_name}, {key_error}')
                    pass


        # isatab_json.pop('Samples', None)
        # del isatab_json['Samples']
        # del isatab_json['Assays']
        files.append(isatab_json)

        os.chdir(root_dir)

    return files

def save_to_file(dictionary):
    with open('data.json', 'w') as outfile:
        json.dump(dictionary, outfile)


def main():
    extract_zips()
    save_to_file(convert())


if __name__ == "__main__":
    main()
