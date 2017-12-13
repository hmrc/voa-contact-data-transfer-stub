/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.voacontactdatatransferstub.utils

import com.typesafe.config.ConfigFactory
import org.scalatest.mockito.MockitoSugar
import play.api.Configuration
import uk.gov.hmrc.voacontactdatatransferstub.SpecBase
import scala.collection.JavaConversions._

class InitializeSpec extends SpecBase with MockitoSugar {

  "Initialize" must {
    "load the schema from the configuration" in {
      val sm = Map("schema" ->
        """
{
  "$schema" : "http://json-schema.org/schema#",
  "description" : "A JSON schema to validate JSON as described in PPM-30048-UEM009-ICD001-HTS-HMRC-Interfaces v1.2.docx",

  "type" : "object",
  "additionalProperties" : false,
  "required": ["version", "timestamp", "domain", "categories", "firstName", "lastName", "email", "phone", "propertyAddress", "message"],
  "properties" : {
    "version" : {
      "type" : "integer",
      "default" : 0
    },
    "timestamp" : {
      "type" : "string"
    },
    "domain" : {
      "type" : "string",
      "pattern": "CT|NDR"
    },
    "categories" : {
      "type" : "array",
      "items" : {
        "type" : "string"
      }
    },
    "firstName" : {
      "type" : "string"
    },
    "lastName" : {
      "type" : "string"
    },
    "email" : {
      "type" : "string"
    },
    "phone" : {
      "type" : "string"
    },
    "propertyAddress": {
      "type": "object",
      "additionalProperties": false,
      "required": ["addressLine1", "town", "postcode"],
      "properties": {
        "addressLine1": {
          "type": "string"
        },
        "addressLine2": {
          "type": "string"
        },
        "town": {
          "type": "string"
        },
        "county": {
          "type": "string"
        },
        "postcode": {
          "type": "string"
        }
      }
    },
    "message": {
      "type": "string"
    }
  }
}
""")
      val conf = new Configuration(ConfigFactory.parseMap(sm))
      val initialize = new Initialize(conf)
      initialize.schema mustBe
        """
{
  "$schema" : "http://json-schema.org/schema#",
  "description" : "A JSON schema to validate JSON as described in PPM-30048-UEM009-ICD001-HTS-HMRC-Interfaces v1.2.docx",

  "type" : "object",
  "additionalProperties" : false,
  "required": ["version", "timestamp", "domain", "categories", "firstName", "lastName", "email", "phone", "propertyAddress", "message"],
  "properties" : {
    "version" : {
      "type" : "integer",
      "default" : 0
    },
    "timestamp" : {
      "type" : "string"
    },
    "domain" : {
      "type" : "string",
      "pattern": "CT|NDR"
    },
    "categories" : {
      "type" : "array",
      "items" : {
        "type" : "string"
      }
    },
    "firstName" : {
      "type" : "string"
    },
    "lastName" : {
      "type" : "string"
    },
    "email" : {
      "type" : "string"
    },
    "phone" : {
      "type" : "string"
    },
    "propertyAddress": {
      "type": "object",
      "additionalProperties": false,
      "required": ["addressLine1", "town", "postcode"],
      "properties": {
        "addressLine1": {
          "type": "string"
        },
        "addressLine2": {
          "type": "string"
        },
        "town": {
          "type": "string"
        },
        "county": {
          "type": "string"
        },
        "postcode": {
          "type": "string"
        }
      }
    },
    "message": {
      "type": "string"
    }
  }
}
"""
    }

    "throw an exception if the schema is not configured" in {
      val sm = Map("key" -> "value")
      val conf = new Configuration(ConfigFactory.parseMap(sm))
      intercept[Exception] {
        val initialize = new Initialize(conf)
      }
    }

  }
}
