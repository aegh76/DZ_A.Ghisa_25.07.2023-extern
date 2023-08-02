
import furhatos.app.blankskill1.flow.Parent
import furhatos.app.demo02.nluAlt.Deutsch
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.PollyVoice
import furhatos.records.User
import furhatos.util.Language



var Benutzer: User? = null

//Der State Greeting erbt von dem State Parent, dort ist das User-Handling definiert.
val Greetinglanguage : State = state(Parent) {
    onEntry {

        //Zu Beginn des States wird definiert, dass Furhat den aktuellen User weiterhin anschaut.
        furhat.attend(users.current)





        furhat.ask("Ich kann Englisch, Deutsch, Türkisch und Rumänisch reden und verstehen, sage mir einfach in welcher Sprache du fortfahren möchtest")

        furhat.setInputLanguage(Language.ENGLISH_US, Language.GERMAN, Language.TURKISH, Language.ROMANIAN)
    }

    onResponse<Türkisch> {
        Benutzer = users.current
        furhat.voice = PollyVoice.Filiz()
        furhat.setInputLanguage(Language.TURKISH)
        Benutzer!!.put("sprache", Language.TURKISH)
        furhatsay(
            furhat = this.furhat,
            germanText = "OK deutsch",
            englishText = "OK english",
            turkishText = "Tamam Türkçe",
            romanianText = "OK Română",
            sprache = Benutzer!!.get("sprache") as Language
        )
        goto(Greeting)
    }
    onResponse<Englisch> {
        Benutzer = users.current
        furhat.voice = PollyVoice.Joey()
        furhat.setInputLanguage(Language.ENGLISH_US)
        Benutzer!!.put("sprache", Language.ENGLISH_US)
        furhatsay(
            furhat = this.furhat,
            germanText = "OK deutsch",
            englishText = "OK english",
            turkishText = "Tamam Türkçe",
            romanianText = "OK Română",
            sprache = Benutzer!!.get("sprache") as Language
        )
        goto(Greeting)
    }
    onResponse<Rumänisch> {
        Benutzer = users.current
        furhat.voice = PollyVoice.Carmen()
        furhat.setInputLanguage(Language.ROMANIAN)
        Benutzer!!.put("sprache", Language.GERMAN)
        furhatsay(
            furhat = this.furhat,
            germanText = "OK deutsch",
            englishText = "OK english",
            turkishText = "Tamam Türkçe",
            romanianText = "OK Română",
            sprache = Benutzer!!.get("sprache") as Language
        )
        goto(Greeting)
    }

    onResponse<Deutsch> {
        Benutzer = users.current
        furhat.voice = PollyVoice.Hans()
        furhat.setInputLanguage(Language.GERMAN)
        Benutzer!!.put("sprache", Language.GERMAN)
        furhatsay(
            furhat = this.furhat,
            germanText = "OK deutsch",
            englishText = "OK english",
            turkishText = "Tamam Türkçe",
            romanianText = "OK Română",
            sprache = Benutzer!!.get("sprache") as Language
        )
        goto(Greeting)
    }
}


