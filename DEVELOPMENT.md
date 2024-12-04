


### Release

Configure the version inside the "gradle.properties" file

Create publishing bundle into Maven Local
```bash
./gradlew publishToMavenLocal
```

Check Maven Local contains release version:
```
ls -l $HOME/.m2/repository/io/accelerate/chlt-schema-lib/$(cat gradle.properties | grep version | cut -d "=" -f2)
```

Publish to Maven Central Staging repo

### Publish to Maven Central

Publish to Maven Central Staging repo
```bash
./gradlew publish
```

A Staging repository is created automatically:
https://oss.sonatype.org/#stagingRepositories

To promote to the Live repo, do the following:
- "Close" the Staging repo, Sonatype Lift will scan the repo for vuln, check the email
- "Refresh" the Staging repos
- "Release" the repo
- wait between 15 mins and up to 2 hours for the new version to appear in Central
- check: https://repo1.maven.org/maven2/ro/ghionoiu/tdl-client-java/
