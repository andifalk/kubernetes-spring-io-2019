#!/bin/sh

gcloud auth login
gcloud config set project pa-afa-kubernetes
gcloud container clusters get-credentials "springio2019"
