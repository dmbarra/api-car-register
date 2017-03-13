this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


Before("@notused") {
    throw new RuntimeException("Never happens")
}

Before("@notused,@important", "@alsonotused") {
    throw new RuntimeException("Never happens")
}


Given(~'^My service is online$') { ->
}

When(~'^I acess the service$') { ->
}

Then(~/^I show message: "([^"]*)"$/) { String arg1 ->
}