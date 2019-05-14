#!/bin/sh

gcloud auth login
gcloud config set project pa-afa-kubernetes

# create KMS keyring
gcloud kms keyrings create "springio2019" \
    --location "europe-west3" \
    --project "pa-afa-kubernetes"

# create KMS encryption key
gcloud kms keys create "gke_springio2019" \
    --location "europe-west3" \
    --keyring "springio2019" \
    --purpose encryption \
    --project "pa-afa-kubernetes"

# grant permission to GKE to use the key
gcloud kms keys add-iam-policy-binding "gke_springio2019" \
  --location "europe-west3" \
  --keyring "springio2019" \
  --member serviceAccount:service-pa-afa-kubernetes@container-engine-robot.iam.gserviceaccount.com \
  --role roles/cloudkms.cryptoKeyEncrypterDecrypter \
  --project "pa-afa-kubernetes"

# create kubernetes cluster using application level encryption
gcloud beta container clusters create "springio2019" \
  --cluster-version=latest \
  --zone "europe-west3-a" \
  --database-encryption-key "projects/pa-afa-kubernetes/locations/europe-west3/keyRings/springio2019/cryptoKeys/gke_springio2019" \
  --project "pa-afa-kubernetes"

