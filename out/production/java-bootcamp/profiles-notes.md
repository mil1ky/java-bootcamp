| Question | Your answer |

| -------- | ----------- |

| Which profile is active when you run plain `mvn package`? |Dev |

| How do you activate `prod` on the command line? |mvn - Pprod  |

| What is the `app.env` value under `dev`? | dev|

| What is the `app.env` value under `prod`? |prod |





&#x20;putting production database passwords inside the `dev` profile;

A: The dev environment is the default environment  everyone has aceess to it. 

\- making `prod` `activeByDefault` on every engineer laptop;

A: it gives access to all engineer when maybe only some require acces it gives room for mistakes because of the broad acess

\- assuming profiles change Java package names (they do not — they change build/config properties);

A: Profiles change configuration settings, not Java package names. The same Java code can be used with different profiles; the profile changes things like database settings or other environment properties.

\- documenting secrets in screenshots of profile properties.

A: thats just a general security risk If someone gets ahold of that info they could alter the secret data or data they are not supposed to have acess to 



Keep `dev` as the laptop default.

Activate `prod` intentionally with `-Pprod`.

Never store real production secrets in `pom.xml` profiles.

