package com.vicpin.krealmextensions.migration

import com.vicpin.krealmextensions.migration.model.TestModel
import com.vicpin.krealmextensions.migration.model.TestModel2
import com.vicpin.krealmextensions.migration.model.TestModel3

/**
 * @author Filip Prochazka (@filipproch)
 */
val migration = realmMigration {
    migrate(fromVersion = 1) {
        create<TestModel> {
            addField<String>("key") {
                primaryKey = true
            }

            removeField("id")

            addField<Long>("count")
        }

        update<TestModel2> {
            addField<String>("field")
        }

        remove<TestModel3>()
    }
}