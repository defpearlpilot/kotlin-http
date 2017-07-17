package dpp.identity


interface Identity
{
  fun tenantId(): String
  fun clientId(): String
  fun secret(): String
}