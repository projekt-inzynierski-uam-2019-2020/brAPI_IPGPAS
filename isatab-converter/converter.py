import csv
import os
import zipfile


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

        isatab_files_dict['s_files'] = [
            x for x in isatab_files if x.startswith('s_')
        ]

        isatab_files_dict['a_files'] = [
            x for x in isatab_files if x.startswith('a_')
        ]

        isatab_files_dict['d_files'] = [
            x for x in isatab_files if x.startswith('d_')
        ]

        with open(isatab_files_dict['s_files'][0], encoding="utf8") as tsv_file:
            reader = csv.DictReader(tsv_file, dialect='excel-tab')

            for row in reader:
                print(row['Characteristics[Organism]'], row['Sample Name']) 

        os.chdir(root_dir)


def main():
    extract_zips()
    convert()


if __name__ == "__main__":
    main()
