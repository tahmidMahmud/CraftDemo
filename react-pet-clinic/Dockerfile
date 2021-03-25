# pull official base image
FROM node:15.11.0-alpine3.13 as build

# set working directory
WORKDIR /app

# install app dependencies
COPY package.json yarn.lock ./
RUN yarn
COPY . ./
RUN yarn build

# add app
COPY . ./

FROM nginx:1.17.1-alpine

EXPOSE 80
COPY --from=build /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
CMD ["nginx", "-g", "daemon off;"]