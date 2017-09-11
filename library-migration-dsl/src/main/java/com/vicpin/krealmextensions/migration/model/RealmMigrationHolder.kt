package com.vicpin.krealmextensions.migration.model

import io.realm.DynamicRealm
import io.realm.RealmSchema

/**
 * @author Filip Prochazka (@filipproch)
 */
data class RealmMigrationHolder(
        val realm: DynamicRealm,
        val oldVersion: Long,
        val newVersion: Long
) {
    val migrationDefs: HashMap<Long, RealmSchema.() -> Unit> = hashMapOf()

    fun migrate(fromVersion: Long, versionMigrator: RealmSchema.() -> Unit) {
        if (migrationDefs.containsKey(fromVersion)) {
            throw IllegalArgumentException("There already is migrator from version $fromVersion")
        }

        migrationDefs.put(fromVersion, versionMigrator)
    }

    fun execute() {
        migrationDefs.toList()
                .sortedBy { it.first }
                .map { (_, migrator) -> migrator }
                .forEach { it.invoke(realm.schema) }
    }

}