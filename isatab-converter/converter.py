import os
import zipfile


def extract_files(directory):
    os.chdir(directory)
    files_in_directory = os.listdir()
    zips_in_directory = [
        x for x in files_in_directory if zipfile.is_zipfile(x)
    ]

    for zip_ in zips_in_directory:
        with zipfile.ZipFile(zip_) as item:
            item.extractall(zip_.replace('.zip', ''))


def main():
    extract_files('.')


if __name__ == "__main__":
    main()
