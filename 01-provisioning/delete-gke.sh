#!/bin/sh

gcloud auth login
gcloud beta container clusters delete "springio2019"

