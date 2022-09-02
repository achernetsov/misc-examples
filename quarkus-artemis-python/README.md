# Overview

Send message from quarkus to python app (qpid proton) through Apache Artemis.

Based on:
1. Quarkus AMPQ example: https://github.com/quarkusio/quarkus-quickstarts/tree/main/amqp-quickstart
2. https://qpid.apache.org/releases/qpid-proton-0.37.0/proton/python/examples/index.html

# Usage

1. Build producer in JVM mode 

> ./mvnw -f amqp-quickstart-producer package

2. Setup AMQP_HOST variable to your IP (192.168...) in ./.env file (or in docker-compose.yml)

3. Run Quarkus producer and Artemis

> docker compose up --build

4. Install python app requirements

Python 3.7-3.9 is needed.

> pip install -r requirements.txt

5. Run python processor

> python processor.py

7. Test

Open http://localhost:8080/quotes.html, click button