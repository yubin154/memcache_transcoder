# memcache_transcoder
===================================

Transcoders that serialize Memcache values and keys compatible with AppEngine
Memcache client. Allow accessing AppEngine Memcache through memcached client
and maintain inter-operable with AppEngine Memcache Client.

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
