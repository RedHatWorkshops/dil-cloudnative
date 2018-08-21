#### Instructions for installing eclipse che for multiple users
# This should be broken off into its own team repo at a later date but for review and work it will stay here for the time being

Each component can be run individually as needed

## These should probably be created in a common project
`oc new-app workshop-infra`

# Che Setup
Either option requires you to configure the wildcard domain

```
export ROUTING_SUFFIX="add wildcard domain"
```
## Single User
```
oc new-project che-single
```

```
oc new-app -f https://raw.githubusercontent.com/RedHatWorkshops/dil-cloudnative/master/admin/components/che/che-server-template.yaml  -p ROUTING_SUFFIX=${ROUTING_SUFFIX}
oc apply -f https://raw.githubusercontent.com/RedHatWorkshops/dil-cloudnative/master/admin/components/che/che-server-pvc.yaml
oc set volume dc/che --add -m /data --name=che-data-volume --claim-name=che-data-volume
```


## Multi-User

```
oc new-project che-multi-user
```

```
oc new-app -f https://raw.githubusercontent.com/RedHatWorkshops/dil-cloudnative/master/admin/components/che/postgres-template.yaml
oc new-app -f https://raw.githubusercontent.com/RedHatWorkshops/dil-cloudnative/master/admin/components/che/keycloak-template.yaml -p ROUTING_SUFFIX=${ROUTING_SUFFIX}
oc apply -f https://raw.githubusercontent.com/RedHatWorkshops/dil-cloudnative/master/admin/components/che/che-server-pvc.yaml
oc new-app -f https://raw.githubusercontent.com/RedHatWorkshops/dil-cloudnative/master/admin/components/che/che-server-template.yaml -p ROUTING_SUFFIX=${ROUTING_SUFFIX} -p CHE_MULTIUSER=true
oc set volume dc/che --add -m /data --name=che-data-volume --claim-name=che-data-volume
```

*** Please evaluate which one you think is best. Either work.
