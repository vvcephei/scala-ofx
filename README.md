scala-ofx
=========

A JVM client library for communicating with financial institutions over OFX.

This is an extremely skeletal implementation right now.

Loosely based / inspired by https://github.com/wesabe/lynofx

Build
-----

compile: ```./sbt compile```
test: ```./sbt test```
single jar: ```./sbt assembly```

Usage
-----

```scala
val pr = new PrettyPrinter(80, 2)

val user = User("username", "password")
val bank = Bank(
  bootstrapUrl = "http://www.example.com",
  fiOrg = "EXAMPLE",
  fiId = "1234",
  appId = "Money",
  appVer = "1400")

// Returns the raw sign-on response. Useful for debugging authentication.
val signon: OfxMessage = bc.signOn()
println(pr format signon.ofx)

// Another raw api call. Returns the bank's ofx profile.
val profile: OfxMessage = bc.profile()
println(pr format profile.ofx)

// Get information about the account
val info: Seq[BankAccountInfo] = bc.accountInfo()

// Download Bank statements:

val accounts: Seq[Account] = Seq(
  Account(routing = "12345", account = "09871", SAVINGS),
  Account(routing = "12345", account = "09871", CHECKING))
val statements: Seq[BankStatementResponse] = bc.bankStatements(accounts, Options.start)

for (statement <- statements) {
  println(s"Balance: ${statement.availableBalance}")
  for (transaction <- statement.transactions) {
    println(s"date: ${transaction.posted}, type: ${transaction.`type`}, amount: ${transaction.amount}, payee: ${transaction.name}, memo: ${transaction.memo}")
  }
}
```

Example CLI
-----------

```bash
./sbt
> run -s 2013-01-01 -c src/main/config/config.yaml -b capitalone360:${USERNAME}:${PASSWORD} -a capitalone360:${ROUTING}:${ACCT_NUM]:SAVINGS -v

# or
./sbt assembly
java -jar target/scala-2.10/scalaofx-assembly-1.0.jar -s 2013-01-01 -c src/main/config/config.yaml -b capitalone360:${USERNAME}:${PASSWORD} -a capitalone360:${ROUTING}:${ACCT_NUM]:SAVINGS -v
```