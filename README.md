# IGR PAN BrAPI Application
[![Build Status](https://travis-ci.org/projekt-inzynierski-uam-2019-2020/brAPI_IPGPAS.svg?branch=dev)](https://travis-ci.org/projekt-inzynierski-uam-2019-2020/brAPI_IPGPAS)
## Content of this repository
This project consists of three main components:
* BrAPI application implementing Breeding API specification based on IGR PAN's database
* Frontend application visually displaying data from the BrAPI application and external BrAPI servers
* Administrative API which serves as a bridge between BrAPI servers and the frontend application. It handles authentication, users, organisations and BrAPI servers 
## How to build the project
### Software required:
* Docker
* Docker Compose
### Steps:
0. Open your favourite terminal emulator
1. Clone this repo using `git clone`
2. Paste IGR PAN's database dump named `PlantPhenoDB.psql` into `database/brapi_db/` directory 
3. `cd` into the cloned repository
4. Run `docker-compose up`
5. To rebuild the project: `docker-compose up --build`

Information on how to install Docker and Docker Compose on your system can be found [here](https://docs.docker.com/install/) and [here](https://docs.docker.com/compose/install/) respectively.
## License
TBD
