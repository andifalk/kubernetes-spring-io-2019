# Spring Cloud on Kubernetes @ Spring I/O 2019

## Demos

* [Spring Boot on Kubernetes (Pure K8s Standard)](hello-spring-kubernetes)
* [Spring Boot on Kubernetes (with Spring Cloud Kubernetes)](hello-spring-cloud-kubernetes)

Please make sure you first create the corresponding
[K8s service account](hello-spring-cloud-kubernetes/k8s/serviceaccount.sh) before
[deploying](hello-spring-cloud-kubernetes/k8s/deploy.sh) the hello-spring-cloud-kubernetes 
app to K8s.

All containers run as NON-ROOT and corresponding 
security context is enforced for pod/container in K8s!