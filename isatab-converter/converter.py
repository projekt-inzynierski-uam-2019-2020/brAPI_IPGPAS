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
            x for x in isatab_files if x.startswith('d_') or x.startswith('data_')
        ]

        isatab_json = {
            'Investigation Identifier': '',
            'Investigation Title': '',
            'Study Title': '',
            'Crop': [],
            'Samples': {},
            'Assays': {}
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
            with open(s_file, encoding='utf-8-sig') as tsv_file:
                reader = csv.DictReader(tsv_file, dialect='excel-tab')
                for row in reader:
                    isatab_json['Crop'].append(row['Characteristics[Organism]'])
                    isatab_json['Crop'] = list(set(isatab_json['Crop']))
                    try:
                        if not row['Characteristics[Infraspecific name]'] in isatab_json['Samples']:
                            isatab_json['Samples'][row['Characteristics[Infraspecific name]']] = []
                        isatab_json['Samples'][row['Characteristics[Infraspecific name]']].append(row['Sample Name'])
                        isatab_json['Samples'][row['Characteristics[Infraspecific name]']] = list(set(isatab_json['Samples'][row['Characteristics[Infraspecific name]']]))
                    except KeyError:
                        print(f'KeyError in the following file: {s_file}')
                        break

        for a_file in isatab_files_dict['a_files']:
            with open(a_file, encoding='utf-8-sig') as tsv_file:
                reader = csv.DictReader(tsv_file, dialect='excel-tab')
                try:
                    for row in reader:
                        for germplasm, samples in isatab_json['Samples'].items():
                            if not germplasm in isatab_json['Assays']:
                                isatab_json['Assays'][germplasm] = {}
                            if not a_file in isatab_json['Assays'][germplasm]:
                                isatab_json['Assays'][germplasm][a_file] = []
                            for sample in samples:
                                if sample == row['Sample Name']:
                                        isatab_json['Assays'][germplasm][a_file].append(row['Assay Name'])
                except KeyError:
                    print(f'KeyError in the following file: {a_file}')
                    break


        for d_file in isatab_files_dict['d_files']:
            with open(d_file, encoding='utf-8-sig') as tsv_file:
                reader = csv.DictReader(tsv_file, dialect='excel-tab')
                    
        # isatab_json.pop('Samples', None)
        del isatab_json['Samples']
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
