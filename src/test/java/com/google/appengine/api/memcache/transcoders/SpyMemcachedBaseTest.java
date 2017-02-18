package com.google.appengine.api.memcache.transcoders;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.ConnectionFactoryBuilder.Protocol;
import net.spy.memcached.MemcachedClient;

abstract class SpyMemcachedBaseTest extends BaseTest  {

  protected static final int DEFAULT_EXP = 0;

  protected InetSocketAddress serverAddress;
  protected String version;
  protected boolean isAscii;

  protected MemcachedClient client;

  protected SpyMemcachedBaseTest(String server, int port, String version, boolean isAscii) {
    this.serverAddress = new InetSocketAddress(server, port);
    this.version = version;
    this.isAscii = isAscii;
  }

  protected void setUp() throws Exception {
    ConnectionFactoryBuilder cfb = new ConnectionFactoryBuilder();
    cfb.setTranscoder(new SpymemcachedSerializingTranscoder());
    cfb.setProtocol(isAscii ? Protocol.TEXT : Protocol.BINARY);
    ArrayList<InetSocketAddress> servers = new ArrayList<>();
    servers.add(serverAddress);
    client = new MemcachedClient(cfb.build(), servers);
  }

  protected void tearDown() throws Exception {
    client.shutdown();
  }

  protected void expectTrue(boolean condition, String template, Object... values) {
    result.append(isAscii ? "ascii " : "binary ");
    super.expectTrue(condition, template, values);
  }

  protected String setRandom(Object value) throws Exception {
    String key = randomKey();
    client.set(key, DEFAULT_EXP, value);
    return key;
  }

  protected String setByKey(String key) throws Exception {
    String value = randomValue();
    client.set(key, DEFAULT_EXP, value);
    return value;
  }
}
