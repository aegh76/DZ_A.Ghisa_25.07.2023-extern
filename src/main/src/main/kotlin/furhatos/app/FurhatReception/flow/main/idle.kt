package furhatos.app.blankskill1.flow.main


import Greeting
import furhat.libraries.standard.UsersLib.usersLib
import furhatos.app.blankskill1.flow.Parent
import furhatos.flow.kotlin.*

val Idle: State = state(Parent) {
    init {
        furhat.usersLib.attendClosestUser()
        if(users.count > 0)
        {
            //Befinden sich mehr als 0 User in Furhat's Reichweite, so leuchtet der LED Ring Furhat's in Magenta.
            furhat.ledStrip.solid(java.awt.Color.MAGENTA)

            //Furhat schaut den ihm nächsten User an
            furhat.usersLib.attendClosestUser()

            //und geht in den nächsten State "(Greetingname") über
            goto(Greeting)
        }
        //Falls genau 0 User in Furhat's Reichweite sind, schaut er niemanden an.
        else(furhat.attendNobody())
    }

    onEntry {
        if (users.count < 1) {
            furhat.attendNobody()
        } else {
            furhat.attend(users.other)
            goto(Greeting)
        }
    }

    onUserEnter {
        furhat.attend(it)
        goto(Greeting)
    }

    onReentry {
        furhat.attend(users.other)
        goto(Greeting)
    }

}
