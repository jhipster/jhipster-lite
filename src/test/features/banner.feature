Feature: Banner

  Scenario: Should add banner JHipster v7
    When I add banner JHipster v7
    Then I should have files in "src/main/resources"
      | banner.txt |

  Scenario: Should add banner JHipster v7 for React
    When I add banner JHipster v7 for React
    Then I should have files in "src/main/resources"
      | banner.txt |

  Scenario: Should add banner JHipster v7 for Vue
    When I add banner JHipster v7 for Vue
    Then I should have files in "src/main/resources"
      | banner.txt |

  Scenario: Should add banner JHipster v2
    When I add banner JHipster v2
    Then I should have files in "src/main/resources"
      | banner.txt |

  Scenario: Should add banner JHipster v3
    When I add banner JHipster v3
    Then I should have files in "src/main/resources"
      | banner.txt |

  Scenario: Should add banner Ippon
    When I add banner Ippon
    Then I should have files in "src/main/resources"
      | banner.txt |
