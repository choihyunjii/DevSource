package com.example.datahub_back.data.treeData

import com.example.datahub_back.data.toolData.*
import com.example.datahub_back.dto.treeDTO.Branch


var branch1 = Branch(
    branchId = 1,
    profile = exampleProfile1,
    project = exampleProject1,
    push = 1,
    pull = 0,
    crash = 0,
    isMainBranch = 0
)

var branch2 = Branch(
    branchId = 2,
    profile = exampleProfile2,
    project = exampleProject2,
    push = 0,
    pull = 0,
    crash = 1,
    isMainBranch = 0
)

var branch3 = Branch(
    branchId = 3,
    profile = null,
    project = exampleProject3,
    push = 0,
    pull = 0,
    crash = 0,
    isMainBranch = 1
)

var branchList = mutableListOf(branch1, branch2, branch3)