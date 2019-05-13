# Hello Spring Kubernetes

This is a demo for using [spring cloud kubernetes](https://spring.io/projects/spring-cloud-kubernetes) to map [Kubernetes](https://kubernetes.io) ConfigMaps and Secrets to Spring property sources.

## Demo application

It contains a simple demo spring boot web application that prints out a hello world message that can be configured via a [Kubernetes ConfigMap](https://kubernetes.io/docs/tasks/configure-pod-container/configure-pod-configmap/). The ConfigMap is read using the Kubernetes client API.

The config map defines these properties:

* hello.message=k8s
* hello.prefix=Hi

As every web application must be secure all endpoints require authentication.
The user credentials are configured using [Kubernetes Secrets](https://kubernetes.io/docs/concepts/configuration/secret/) mounted as volumes. This is recommended in favour of environment variables as these can easily be leaked into logs or via ```kubectl describe pod``` command.

You may read the secrets using the Kubernetes client API as well. But this is not recommended as you have to give
your deployed application rights to read all secrets in same namespace which might be a high security risk.

The user credentials are configured by these properties:

* user.username
* user.password
* admin.username
* admin.password

The contained demo spring boot application exposes a simple RESTful API:

* http://localhost:8080 - GET a configured hello world message
* http://localhost:8080 - POST with a custom message in request body
* http://localhost:8080/actuator - Actuator endpoints for monitoring

## Deploy the application

The corresponding container image for this application is already 
published on [docker hub](https://hub.docker.com).
You can pull it from [there](https://hub.docker.com/r/andifalk/hello-spring-kubernetes) using:

```
docker pull andifalk/hello-spring-kubernetes:latest
```

__Note__:   
The container image uses a [Distroless Base Image for Java](https://github.com/GoogleContainerTools/distroless). This reduces not only the size of the resulting image but reduces the attack surface by only including the pure minimum that is required to run a Java application in a container.

To deploy the container image to a Kubernetes cluster you first have to create the corresponding ConfigMap and Secrets:

```
kubectl create -f k8s/configmap.yaml
kubectl create -f k8s/secrets.yaml
```

When using the spring cloud kubernetes project in a Kubernetes cluster with RBAC enabled it requires configuring a service account for the application. Otherwise the application cannot access the ConfigMaps.
The following commands create the required role for read access to Pods, Services, ConfigMaps, Namespaces and Endpoints. A service account is created (is used by the later app deployment manifest). Finally a role binding between the service account and the role for read access is created.

```
kubectl create -f k8s/read-access-role.yaml
kubectl create -f k8s/serviceaccount.yaml
kubectl create -f k8s/rolebinding-read-access.yaml
```

Now we are set up to deploy our spring boot application using:

```
kubectl create -f k8s/deployment.yaml
```

Please note that the deployment also enforces a pod context security policy that prevents root priviliges on the pod for the docker container.

```
...
securityContext:
    allowPrivilegeEscalation: false
    runAsNonRoot: true 
...    
```            

To access the deployed web application you need to expose it as a service or just use port forwarding directly to the Pod like this:

```
kubectl port-forward pod/[Pod name] 8080:8080
```

You have to replace [Pod name] with your concrete Pod name. You can get the name using:

```
kubectl get pods
```

With port forwarding enabled you should be able to access the deployed application using [localhost:8080](http://localhost:8080).
In the login dialog you need to use the credentials as configured in the _secrets.yaml_ manifest.

user/k8s_user
admin/k8s_admin