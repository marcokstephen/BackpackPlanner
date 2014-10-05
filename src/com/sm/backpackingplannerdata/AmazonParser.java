

import java.io.IOException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;


public class AmazonParser {
	private static final String ns = null;

public List parse(InputStream in) throws XmlPullParserException, IOException {
    try {
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(in, null);
        parser.nextTag();
        return readFeed(parser);
    } finally {
        in.close();
    }
}

private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
    List entries = new ArrayList();

    parser.require(XmlPullParser.START_TAG, ns, "feed");
    while (parser.next() != XmlPullParser.END_TAG) {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            continue;
        }
        String name = parser.getName();
        // Starts by looking for the Item tag
        if (name.equals("Item")) {
            entries.add(readItem(parser));
        } else {
          skip(parser);
        }
    }  
    return entries;
}

public static class Item {
    public final String Brand;
    public final String Feature;
    public final String Title;
    public final String ASIN;

    private Item(String Brand, String Feature, String Title, String ASIN) {
        this.Brand = Brand;
        this.Feature = Feature;
        this.Title = Title;
        this.ASIN = ASIN;
    }
}
  
// Parses the contents of an Item. If it encounters a title, summary, or link tag, hands them off
// to their respective "read" methods for processing. Otherwise, skips the tag.
private Item readItem(XmlPullParser parser) throws XmlPullParserException, IOException {
    parser.require(XmlPullParser.START_TAG, ns, "Item");
    String Brand = null;
    String Feature = null;
    String Title = null;
    String ASIN = null; 
    while (parser.next() != XmlPullParser.END_TAG) {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            continue;
        }
        String name = parser.getName();
        if (name.equals("Brand")) {
            Brand = readBrand(parser);
        } else if (name.equals("Feature")) {
            Feature = readFeature(parser);
        } else if (name.equals("Title")) {
            Title = readTitle(parser);
        } else if (name.equals("ASIN")) {
            ASIN = readASIN(parser);
        } else {
         skip(parser);
        }
    }
    return new Item(Brand, Feature, Title, ASIN);
}

// Processes title tags in the feed.
private String readBrand(XmlPullParser parser) throws IOException, XmlPullParserException {
    parser.require(XmlPullParser.START_TAG, ns, "Brand");
    String Brand = readText(parser);
    parser.require(XmlPullParser.END_TAG, ns, "Brand");
    return Brand;
}
  
// Processes Title tags in the feed.
private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
    String Title = "";
    parser.require(XmlPullParser.START_TAG, ns, "Title");
    String tag = parser.getName();
    String relType = parser.getAttributeValue(null, "rel");  
    if (tag.equals("Title")) {
        if (relType.equals("alternate")){
            Title = parser.getAttributeValue(null, "href");
            parser.nextTag();
        } 
    }
    parser.require(XmlPullParser.END_TAG, ns, "Title");
    return Title;
}

// Processes summary tags in the feed.
private String readFeature(XmlPullParser parser) throws IOException, XmlPullParserException {
    parser.require(XmlPullParser.START_TAG, ns, "Feature");
    String Feature = readText(parser);
    parser.require(XmlPullParser.END_TAG, ns, "Feature");
    return Feature;
}

private String readASIN(XmlPullParser parser) throws IOException, XmlPullParserException {
    parser.require(XmlPullParser.START_TAG, ns, "ASIN");
    String ASIN = readText(parser);
    parser.require(XmlPullParser.END_TAG, ns, "ASIN");
    return ASIN;
}

// For the tags title and Feature, extracts their text values.
private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
    String result = "";
    if (parser.next() == XmlPullParser.TEXT) {
        result = parser.getText();
        parser.nextTag();
    }
    return result;
}

private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
    if (parser.getEventType() != XmlPullParser.START_TAG) {
        throw new IllegalStateException();
    }
    int depth = 1;
    while (depth != 0) {
        switch (parser.next()) {
        case XmlPullParser.END_TAG:
            depth--;
            break;
        case XmlPullParser.START_TAG:
            depth++;
            break;
        }
    }
 }
  
}




