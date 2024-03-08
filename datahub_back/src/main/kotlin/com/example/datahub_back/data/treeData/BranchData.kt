package com.example.datahub_back.data.treeData

import com.example.datahub_back.data.toolData.exampleProfile1
import com.example.datahub_back.data.toolData.exampleProfile2
import com.example.datahub_back.data.toolData.exampleProjectList
import com.example.datahub_back.dto.treeDTO.Branch


var branch1 = Branch(
    branchId = 1,
    profile = exampleProfile1,
    project = exampleProjectList[0],
    push = 1,
    pull = 0,
    crash = 0,
    isMainBranch = 0
)

var branch2 = Branch(
    branchId = 2,
    profile = exampleProfile2,
    project = exampleProjectList[0],
    push = 0,
    pull = 0,
    crash = 1,
    isMainBranch = 0
)

var branch3 = Branch(
    branchId = 3,
    profile = null,
    project = exampleProjectList[0],
    push = 0,
    pull = 0,
    crash = 0,
    isMainBranch = 1
)

var branchList = mutableListOf(branch1, branch2, branch3)