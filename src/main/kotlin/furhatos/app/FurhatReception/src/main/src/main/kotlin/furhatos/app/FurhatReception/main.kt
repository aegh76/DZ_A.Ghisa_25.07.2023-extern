package furhatos.app.blankskill1

import furhatos.app.blankskill1.flow.Init
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill

class Blankskill1Skill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
