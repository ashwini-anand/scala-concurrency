package controllers;

import play.mvc.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

  /**
   * An action that renders an HTML page with a welcome message.
   * The configuration in the <code>routes</code> file means that
   * this method will be called when the application receives a
   * <code>GET</code> request with a path of <code>/</code>.
   */
  public Result index() {
    return ok(views.html.index.render());
  }

  public Result explore() {
    return ok(views.html.explore.render());
  }

  public Result tutorial() {
    return ok(views.html.tutorial.render());
  }

  static HashMap<String, Integer> cache = new HashMap<String, Integer>();
  static final int capacity = 1000;
  private static Object updateLock = new Object();
  private static Object writeLock = new Object();


  public Result setKey(String key, int value) {
    if (cache.size() > capacity) {
      return badRequest("bad request");
    } else {
      synchronized (writeLock) {
        cache.put(key, value);
      }
      return ok("created");
    }
  }

  public Result get(String key) {
    if (cache.containsKey(key)) {
      return ok("" + cache.get(key));
    } else {
      return notFound("not found");
    }
  }

  public Result getCacheSize() {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ObjectOutputStream oos = null;
    try {
      oos = new ObjectOutputStream(baos);
      oos.writeObject(cache);
      oos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ok("size of cache in KB " + baos.size());
  }

  public Result increment(String key) {
    synchronized (updateLock) {
      int vall = cache.get(key);
      vall++;
      cache.put(key, vall);
    }
    return ok("value for " + key + " incremented");
  }

  private static void incr(String key) {
    synchronized (updateLock) {
      int vall = cache.get(key);
      vall++;
      cache.put(key, vall);
    }
  }

  public Result getAllMatching(String pattern) {
    String res = "";
    for (String key : cache.keySet()) {
      if (key.startsWith(pattern)) {
        res = res + " " + key;
      }
    }
    return ok(res);
  }


}
