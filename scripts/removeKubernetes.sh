#!/usr/bin/env bash

echo "Removendo deployments e statefulsets no kubernetes..."
kubectl -n java-crud delete deployment adminer crud
kubectl -n java-crud delete statefulset mysql

echo "Removendo services no kubernetes..."
kubectl -n java-crud delete svc mysql-service adminer-service crud-service

echo "Removendo ingress no kubernetes..."
kubectl -n java-crud delete ingress adminer-ingress crud-ingress