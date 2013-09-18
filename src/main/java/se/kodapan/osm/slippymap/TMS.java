package se.kodapan.osm.slippymap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author kalle
 * @since 8/25/13 1:29 PM
 */
public class TMS extends SlippyMap {

  private static Logger log = LoggerFactory.getLogger(TMS.class);

  public TMS() {
  }

  public TMS(String urlPattern) {
    super(urlPattern);
  }

  public Tile tileFactory(double longitude, double latitude, int z) {
    int x = (int) Math.floor((longitude + 180) / 360 * (1 << z));
    int y = (int) Math.floor((1 - Math.log(Math.tan(Math.toRadians(latitude)) + 1 / Math.cos(Math.toRadians(latitude))) / Math.PI) / 2 * (1 << z));

    int invertedY = (1 << z) - y - 1;

    return new Tile(x, invertedY, z);
  }

  public List<Tile> listTiles(double southLatitude, double westLongitude, double northLatitude, double eastLongitude, int z) {
    final Tile northEast = tileFactory(northLatitude, eastLongitude, z);
    final Tile southWest = tileFactory(southLatitude, westLongitude, z);

    List<Tile> tiles = new ArrayList<Tile>();
    for (int x = southWest.getX(); x < northEast.getX(); x++) {
      for (int y = southWest.getY(); y < northEast.getY(); y++) {
        tiles.add(new Tile(x, y, z));
      }
    }

    return tiles;
  }


}