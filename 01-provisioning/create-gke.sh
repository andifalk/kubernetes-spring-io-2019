#!/bin/sh

gcloud auth login
gcloud beta container --project "symbolic-folio-232616" clusters create "demo-cluster" --zone "europe-west3-a" --no-enable-basic-auth --cluster-version "1.12.6-gke.10" --machine-type "n1-standard-1" --image-type "COS" --disk-type "pd-standard" --disk-size "100" --metadata disable-legacy-endpoints=true --scopes "https://www.googleapis.com/auth/devstorage.read_only","https://www.googleapis.com/auth/logging.write","https://www.googleapis.com/auth/monitoring","https://www.googleapis.com/auth/servicecontrol","https://www.googleapis.com/auth/service.management.readonly","https://www.googleapis.com/auth/trace.append" --num-nodes "3" --enable-cloud-logging --enable-cloud-monitoring --no-enable-ip-alias --network "projects/symbolic-folio-232616/global/networks/default" --subnetwork "projects/symbolic-folio-232616/regions/europe-west3/subnetworks/default" --addons HorizontalPodAutoscaling,HttpLoadBalancing --enable-autoupgrade --enable-autorepair

