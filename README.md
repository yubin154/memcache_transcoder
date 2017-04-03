# memcache_transcoder
===================================

NOTE(rodrigo): this is a Google-developed library to allow compatibility of
Memcache key serialization outside of AppEngine Memcache. The library doesn't
live in any publicly available Maven repo, so we forked it here to install
it in our own.

---

Transcoders that serialize Memcache values and keys compatible with AppEngine
Memcache client. Allow accessing AppEngine Memcache through memcached client
and maintain inter-operable with AppEngine Memcache Client.

Build transcoder artifact in the local maven repository

    cd memcache_transcoder
    mvn clean install

Add in target project pom.xml

    <dependency>
      <groupId>com.google.appengine</groupId>
      <artifactId>memcache-transcoders</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>

Use the transcoder in spymemcached client

    ConnectionFactoryBuilder cfb = new ConnectionFactoryBuilder();
    cfb.setTranscoder(new SpymemcachedSerializingTranscoder());
    cfb.setProtocol(Protocol.BINARY);
    ArrayList<InetSocketAddress> servers = new ArrayList<>();
    servers.add(new InetSocketAddress("host", port));
    client = new MemcachedClient(cfb.build(), servers);

GAE Java Memcache API allows Java object to be used as key, memcached allows
only string to be used as key.


    com.google.appengine.api.memcache.transcoders.Serialization.makeKey
    com.google.appengine.api.memcache.transcoders.Serialization.makeKeys
    
can be used to encode raw java object to string keys that are compatible with
GAE Memcache API.

