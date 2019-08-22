public class Codec {
    private static final char[] alphabet = 
        "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNNM".toCharArray();

    private static Map<String, String> map = new HashMap<>();
    private static Map<String, String> reverseMap = new HashMap<>();
    private static Random random = new Random();
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String shortUrl =  map.get(longUrl);
        if (shortUrl == null) {
            do {
                shortUrl = getCode();
            } while (reverseMap.containsKey(shortUrl));
            map.put(longUrl, shortUrl);
            reverseMap.put(shortUrl, longUrl);
        }
        return shortUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return reverseMap.get(shortUrl);
    }
    
    private String getCode() {
        StringBuilder builder = new StringBuilder("http://tinyurl.com/");
        for (int i = 0; i < 6; ++i) {
            builder.append(alphabet[random.nextInt(alphabet.length)]);
        }
        return builder.toString();
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
