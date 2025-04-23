/*
 * SPDX-FileCopyrightText: Copyright (c) 2016-2025 Objectionary.com
 * SPDX-License-Identifier: MIT
 */

import { lint as lintSync } from "markdownlint/sync";

globalThis.lint = function(text) {
  const options = {
    "strings": {
      "file": text
    }
  };

  const results = lintSync(options);
  return results["file"];
}
