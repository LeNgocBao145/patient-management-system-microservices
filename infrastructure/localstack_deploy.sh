#!/bin/bash
set -e

# Đã có export ở ngoài terminal nhưng cứ khai báo lại cho chắc chắn trong script
export AWS_ACCESS_KEY_ID=test
export AWS_SECRET_ACCESS_KEY=test
export AWS_DEFAULT_REGION=ap-southeast-1
ENDPOINT="http://localhost:4566"

aws --endpoint-url=$ENDPOINT cloudformation delete-stack --stack-name patient-management

aws --endpoint-url=$ENDPOINT cloudformation package \
    --template-file ./cdk.out/localstack.template.json \
    --s3-bucket cdk-assets \
    --output-template-file packaged.yaml

aws --endpoint-url=$ENDPOINT cloudformation deploy \
    --template-file packaged.yaml \
    --stack-name patient-management

aws --endpoint-url=$ENDPOINT elbv2 describe-load-balancers \
      --query "LoadBalancers[0].DNSName" \
      --output text