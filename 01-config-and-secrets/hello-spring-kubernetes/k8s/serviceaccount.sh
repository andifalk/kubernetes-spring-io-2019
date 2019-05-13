#!/bin/sh

kubectl apply -f ./read-access-role.yaml
kubectl apply -f ./serviceaccount.yaml
kubectl apply -f ./rolebinding-read-access.yaml
