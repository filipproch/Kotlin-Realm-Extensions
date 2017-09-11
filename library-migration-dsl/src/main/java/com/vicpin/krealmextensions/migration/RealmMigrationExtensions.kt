package com.vicpin.krealmextensions.migration

import com.vicpin.krealmextensions.migration.model.RealmMigrationHolder
import com.vicpin.krealmextensions.migration.model.RealmObjectSchemaField
import io.realm.RealmMigration
import io.realm.RealmModel
import io.realm.RealmObjectSchema
import io.realm.RealmSchema

/**
 * Created by filipproch
 * DSL extensions for writing Realm migrations
 */

inline fun realmMigration(crossinline migrationBuilder: RealmMigrationHolder.() -> Unit) = RealmMigration { realm, oldVersion, newVersion ->
    RealmMigrationHolder(realm, oldVersion, newVersion)
            .apply(migrationBuilder)
            .execute()
}

inline fun <reified T : RealmModel> RealmSchema.create(modelBuilder: RealmObjectSchema.() -> Unit) {
    create(T::class.java.simpleName).apply(modelBuilder)
}

inline fun <reified T : RealmModel> RealmSchema.remove() {
    remove(T::class.java.simpleName)
}

inline fun <reified T : RealmModel> RealmSchema.update(modelBuilder: RealmObjectSchema.() -> Unit) {
    get(T::class.java.simpleName).apply(modelBuilder)
}

inline fun <reified T> RealmObjectSchema.addField(name: String) {
    addField<T>(name, {})
}

inline fun <reified T> RealmObjectSchema.addField(name: String, fieldBuilder: RealmObjectSchemaField.() -> Unit) {
    RealmObjectSchemaField().apply(fieldBuilder).let {
        addField(name, T::class.java, *it.attributes)
    }
}