#!/bin/bash

IS_GREEN=$(docker ps | grep green) # 현재 실행중인 App이 blue인지 확인합니다.
DEFAULT_CONF=" /etc/nginx/nginx.conf"

if [ -z $IS_GREEN  ];then # blue라면

  echo "### CHANGE BLUE TO GREEN ###"

  echo "1. Pull green"
  docker-compose pull green # green으로 이미지를 내려받습니다.

  echo "2. Green container up"
  docker-compose up -d green # green 컨테이너 실행

  while [ 1 = 1 ]; do
  echo "3. Green health check..."
  sleep 3

  REQUEST=$(curl http://127.0.0.1:8080) # green으로 request
    if [ -n "$REQUEST" ]; then # 서비스 가능하면 health check 중지
            echo "health check success"
            break ;
            fi
  done;

  echo "4. Reload NGINX"
  sudo cp /nginx/conf.d/nginx.green.conf /etc/nginx/nginx.conf
  sudo nginx -s rel

  echo "5. Blue container down"
  docker-compose stop blue
else
  echo "### CHANGE GREEN TO BLUE ###"

  echo "1. Pull BLUE"
  docker-compose pull blue

  echo "2. Blue container up"
  docker-compose up -d blue

  while [ 1 = 1 ]; do
    echo "3. Blue health check..."
    sleep 3
    REQUEST=$(curl http://127.0.0.1:8081) # blue로 request

    if [ -n "$REQUEST" ]; then # 서비스 가능하면 health check 중지
      echo "health check success"
      break ;
    fi
  done;

  echo "4. reload nginx"
  sudo cp /nginx/conf.d/nginx.blue.conf /etc/nginx/nginx.conf
  sudo nginx -s reload

  echo "5. green container down"
  docker-compose stop green
fi