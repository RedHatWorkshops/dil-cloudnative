#### Instructions for installing eclipse che for multiple users


```
$ export ROUTING_SUFFIX=...
$ oc new-project che
$ oc new-app -f postgres-template.yaml
$ oc new-app -f keycloak-template.yaml -p ROUTING_SUFFIX=${ROUTING_SUFFIX}
$ oc apply -f che-server-pvc.yaml
$ oc new-app -f che-server-template.yaml -p ROUTING_SUFFIX=${ROUTING_SUFFIX} -p CHE_MULTIUSER=true
$ oc set volume dc/che --add -m /data --name=che-data-volume --claim-name=che-data-volume
```
