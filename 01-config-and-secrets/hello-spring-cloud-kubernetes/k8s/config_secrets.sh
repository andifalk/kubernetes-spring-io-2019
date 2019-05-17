#!/bin/sh

kubectl apply -f ./configmap-yaml.yaml
kubectl apply -f ./secrets.yaml
