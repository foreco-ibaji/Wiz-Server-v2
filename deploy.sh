#!/bin/bash
#redis -

#echo "redis 이미지 받아오기"
#docker-compose pull redis
#
#echo "redis 실행"
#docker-compose up -d redis

#redis -
docker-compose stop green

echo "그린 이미지 받아오기"
docker-compose pull green

echo "그린 실행"
docker-compose up -d green

# health check

while [ 1 = 1 ]; do
echo "헬스 체크 중"
sleep 3

REQUEST=$(curl http://127.0.0.1:8080)
  if [ -n "$REQUEST" ]; then
          echo "헬스 체크 성공"
          break ;
          fi
done;

#echo "nginx conf 파일 교체"
#sudo cp ./nginx.green.conf /etc/nginx/nginx.conf
#sudo nginx -s reload


docker-compose stop blue

echo "블루 이미지 받아오기"
docker-compose pull blue

echo "블루 실행"
docker-compose up -d blue

# health check
while [ 1 = 1 ]; do
  echo "헬스 체크 중"
  sleep 3
  REQUEST=$(curl http://127.0.0.1:8081)

  if [ -n "$REQUEST" ]; then
    echo "헬스 체크 성공"
    break ;
  fi
done;

#echo "nginx conf 파일 교체"
#sudo cp ./nginx.default.conf /etc/nginx/nginx.conf
#sudo nginx -s reload
