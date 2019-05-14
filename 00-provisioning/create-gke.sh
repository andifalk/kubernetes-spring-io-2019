#!/bin/sh

gcloud auth login
gcloud config set project pa-afa-kubernetes

# create kubernetes cluster only using encryption at rest on volume level
gcloud beta container clusters create "springio2019" \
  --cluster-version=latest \
  --zone "europe-west3-a" \
  --project "pa-afa-kubernetes"

