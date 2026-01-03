# Caminhos
$propFile = "app/version.properties"
$jsonFile = "version.json"

# Ler propriedades
$content = Get-Content $propFile
$codeLine = $content | Where-Object { $_ -match "VERSION_CODE=" }
$nameLine = $content | Where-Object { $_ -match "VERSION_NAME=" }

$currentCode = [int]($codeLine -replace "VERSION_CODE=", "")
$currentName = ($nameLine -replace "VERSION_NAME=", "")

# Incrementar
$newCode = $currentCode + 1
# Simples incremento de patch (assumindo x.y ou x.y.z)
$parts = $currentName.Split(".")
if ($parts.Count -gt 0) {
    $parts[-1] = [int]$parts[-1] + 1
    $newName = $parts -join "."
} else {
    $newName = "$currentName.1"
}

# Salvar propriedades
$newContent = "VERSION_CODE=$newCode`nVERSION_NAME=$newName"
Set-Content $propFile $newContent

Write-Host "Versão atualizada: $currentName ($currentCode) -> $newName ($newCode)"

# Atualizar version.json
$jsonContent = Get-Content $jsonFile -Raw
$json = $jsonContent | ConvertFrom-Json
$json.versionCode = $newCode
$json.versionName = $newName
$json | ConvertTo-Json -Depth 10 | Set-Content $jsonFile

Write-Host "version.json atualizado."

# Build
Write-Host "Iniciando build..."
./gradlew assembleDebug

Write-Host "Build concluída!"
