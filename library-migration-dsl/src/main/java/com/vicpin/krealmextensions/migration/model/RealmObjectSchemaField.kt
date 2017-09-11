package com.vicpin.krealmextensions.migration.model

import io.realm.FieldAttribute

/**
 * @author Filip Prochazka (@filipproch)
 */
class RealmObjectSchemaField {
    var primaryKey: Boolean = false
    var indexed: Boolean = false
    var required: Boolean = false

    val attributes: Array<FieldAttribute>
        get() {
            val attrs = mutableListOf<FieldAttribute>()

            if (primaryKey) {
                attrs.add(FieldAttribute.PRIMARY_KEY)
            }

            if (indexed) {
                attrs.add(FieldAttribute.INDEXED)
            }

            if (required) {
                attrs.add(FieldAttribute.REQUIRED)
            }

            return attrs.toTypedArray()
        }
}