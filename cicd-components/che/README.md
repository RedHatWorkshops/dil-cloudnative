#### Instructions for installing eclipse che for multiple users


```
$ oc new-project che
$ oc new-app -f multi/postgres-template.yaml
$ oc new-app -f multi/keycloak-template.yaml -p ROUTING_SUFFIX=${ROUTING_SUFFIX}
$ oc apply -f pvc/che-server-pvc.yaml
$ oc new-app -f che-server-template.yaml -p ROUTING_SUFFIX=${ROUTING_SUFFIX} -p CHE_MULTIUSER=true
$ oc set volume dc/che --add -m /data --name=che-data-volume --claim-name=che-data-volume
```