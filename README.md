# LifeFeed-Android
Share your daily life with the world.

# Deploy

* Create `app/fabric.properties` and put inside it:

```
#Contains API Secret used to validate your application. Commit to internal source control; avoid making secret public.
#Mon Jan 16 18:43:59 CST 2017
apiSecret=apiSecretFromFabric
apiKey=apiKeyFromFabric
```

* Create `app/src/main/res/raw/twittercreds.json` and put inside:

```
{
  "key": "twitter api key",
  "secret": "twitter api secret"
}
```
