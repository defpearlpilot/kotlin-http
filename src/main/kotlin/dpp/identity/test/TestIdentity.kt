package dpp.identity.test

import dpp.identity.Identity


class TestIdentity : Identity
{
  override fun tenantId(): String
  {
    return "575c5b98-c069-4475-99d9-cfb32e39d24e"
  }

  override fun clientId(): String
  {
    return "123999c5-771d-4136-9d87-b5fc03f3266e"
  }

  override fun secret(): String
  {
    return ""
  }

}