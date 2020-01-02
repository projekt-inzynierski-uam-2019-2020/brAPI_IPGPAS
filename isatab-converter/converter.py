import csv
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
            'Samples': {}
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
            # print(s_file)
            with open(s_file, encoding='utf8') as tsv_file:
                reader = csv.DictReader(tsv_file, dialect='excel-tab')
                for row in reader:
                    isatab_json['Crop'].append(row['Characteristics[Organism]'])
                    isatab_json['Crop'] = list(set(isatab_json['Crop']))
                    try:
                        # isatab_json['Samples'][row['Characteristics[Infraspecific name]']] = row['Sample Name']
                        isatab_json['Samples'] = row['Sample Name'] # dict(zip(row['Sample Name'], row['Characteristics[Infraspecific name]']))
                        
                    except KeyError:
                        pass
                    

        pprint.pprint(isatab_json)

        os.chdir(root_dir)


def main():
    extract_zips()
    convert()


if __name__ == "__main__":
    main()
