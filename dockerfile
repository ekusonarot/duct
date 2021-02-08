FROM clojure

RUN apt-get update && \
    apt install net-tools && \
    apt-get -y install git