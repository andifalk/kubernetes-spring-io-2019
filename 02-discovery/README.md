# Spring Cloud on Kubernetes @ Spring I/O 2019

## Demos

* [Spring Boot discovery client (with Spring Cloud Kubernetes)](question-client)  
* [Spring Boot discovered service](answering-service)  

Please make sure you first create the corresponding
[K8s service account](question-client/k8s/serviceaccount.sh) before
[deploying](question-client/k8s/deploy.sh) the discovery client to K8s.

All containers run as NON-ROOT and corresponding 
security context is enforced for pod/container in K8s!

