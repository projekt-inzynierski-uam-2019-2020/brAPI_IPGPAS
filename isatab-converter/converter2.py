import csv
import json
import os
import zipfile

class Converter:

    def __init__(self, path, file_name):
        self.path = path
        self.file_name = file_name


    def _extract_zips(self):
        os.chdir(self.path)
        zips_in_directory = [x for x in os.listdir() if zipfile.is_zipfile(x)]
        for zip_ in zips_in_directory:
            with zipfile.ZipFile(zip_) as item:
                item.extractall()


    def _save_to_file(self, data, name):
        with open(name, 'w') as outfile:
            json.dump(data, outfile)


    def _convert(self):
        os.chdir(self.path)
        directories = [x for x in os.listdir() if os.path.isdir(x)]
        out_json = []

        for dir_ in directories:
            print(dir_)
            os.chdir(dir_)
            files_in_dir = os.listdir()
            i_files = [x for x in files_in_dir if x.startswith('i_')]
            s_files = [x for x in files_in_dir if x.startswith('s_')]
            a_files = [x for x in files_in_dir if x.startswith('a_')]
            out_data = {
                'Samples' : {},
                'Assays' : {}
            }

            with open(i_files[0], encoding="utf8") as i_file:
                for line in i_file:
                    if 'Investigation Identifier' in line:
                        out_data['Investigation Identifier'] = line.replace('Investigation Identifier', '').strip()
                    elif 'Investigation Title' in line:
                        out_data['Investigation Title'] = line.replace('Investigation Title', '').strip()
                    elif 'Study Title' in line:
                        out_data['Study Title'] = line.replace('Study Title', '').strip()

            for s_file in s_files:
                with open(s_file, encoding="utf8") as tsv_file:
                    reader = csv.DictReader(tsv_file, dialect="excel-tab")
                    try:
                        for row in reader:
                            organism = row['Characteristics[Organism]']
                            germplasm = row['Characteristics[Infraspecific name]']
                            sample_name = row['Sample Name']

                            if 'Crop' not in out_data:
                                out_data['Crop'] = organism

                            if s_file not in out_data['Samples']:
                                out_data['Samples'][s_file] = {}

                            out_data['Samples'][s_file][sample_name] = germplasm

                    except KeyError as key_error:
                        print(f'No such key: {key_error} in file: {s_file}')
                        break

            for a_file in a_files:
                with open(a_file, encoding="utf8") as tsv_file:
                    reader = csv.DictReader(tsv_file, dialect="excel-tab")
                    try:
                        for row in reader:
                            data_file = row['Derived Data File']
                            sample_name  = row['Sample Name']
                            assay_name = row['Assay Name']

                            if sample_name not in out_data['Assays']:
                                out_data['Assays'][sample_name] = {}

                            if data_file not in out_data['Assays'][sample_name]:
                                out_data['Assays'][sample_name][data_file] = []

                            out_data['Assays'][sample_name][data_file].append(assay_name)


                    except KeyError as key_error:
                        print(f'No such key: {key_error} in file: {a_file}')
                        break

            out_json.append(out_data)
            os.chdir('..')

        return out_json


    def run(self):
        self._extract_zips()
        self._save_to_file(self._convert(), self.file_name)


def main():
    c = Converter('.', 'out.json')
    c.run()


if __name__ == "__main__":
    main()