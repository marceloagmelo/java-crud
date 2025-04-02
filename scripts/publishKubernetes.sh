#!/usr/bin/env bash

echo "Publicando mysql no kubernetes..."
awk 'FNR==1 {print "---"}{print}' k8s/mysql/*.yaml | envsubst > kub-app.yaml
cat kub-app.yaml
kubectl apply -f kub-app.yaml -n java-crud

echo "Publicando adminer no kubernetes..."
awk 'FNR==1 {print "---"}{print}' k8s/adminer/*.yaml | envsubst > kub-app.yaml
cat kub-app.yaml
kubectl apply -f kub-app.yaml -n java-crud

echo "Publicando consumer no kubernetes..."
awk 'FNR==1 {print "---"}{print}' k8s/crud/*.yaml | envsubst > kub-app.yaml
cat kub-app.yaml
kubectl apply -f kub-app.yaml -n java-crud
