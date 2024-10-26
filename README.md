# 🧪 PotionBlacklist

[![Minecraft Version](https://img.shields.io/badge/Minecraft-1.19.2-brightgreen.svg)]()
[![License](https://img.shields.io/badge/License-MIT-blue.svg)]()
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)]()

A powerful and lightweight Spigot plugin that allows server administrators to restrict potion usage for specific players.

## ✨ Features

- 🎯 Blacklist specific players from using any type of potions
- 🛡️ Comprehensive protection against all potion types (normal, splash, lingering)
- 💾 Persistent storage of blacklisted players
- 🎨 Visual feedback with particles when blocking potion usage
- 🔄 Toggle system for easy blacklist management
- ⚡ Lightweight and optimized performance
- 🎮 Simple and intuitive commands

## 📋 Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/potion blacklist <player>` | Add/remove a player from the potion blacklist | `potion.blacklist` |

## 🔑 Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `potion.blacklist` | Allows managing the potion blacklist | op |
| `potion.blacklist.exempt` | Makes a player immune to being blacklisted | false |

## 📥 Installation

1. Download the latest version of PotionBlacklist from the releases page
2. Place the JAR file in your server's `plugins` folder
3. Restart your server
4. The plugin will automatically create necessary configuration files

## ⚙️ Configuration

### config.yml
```yaml
settings:
  # Whether to show particles when blocking potion usage
  show-particles: true
  # Whether to show attempted potion type
  show-potion-type: true
```

### blacklist.yml
```yaml
# List of blacklisted player UUIDs
blacklisted-players: []
```

## 🚀 Usage Examples

1. Blacklist a player:
```
/potion blacklist Steve
```

2. Remove a player from blacklist:
```
/potion blacklist Steve
```
(Running the command again toggles the blacklist status)

## 🔧 Building from Source

1. Clone the repository:
```bash
git clone https://github.com/MrTroxy/PotionBlacklist.git
```

2. Build using Maven:
```bash
mvn clean package
```

The compiled JAR will be in the `target` directory.

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/improvement`)
3. Make your changes
4. Commit your changes (`git commit -am 'Add new feature'`)
5. Push to the branch (`git push origin feature/improvement`)
6. Create a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🐛 Bug Reports

Found a bug? Please create an issue with the following information:
- Detailed description of the bug
- Steps to reproduce
- Server version
- Plugin version
- Any relevant error messages

## 💡 Feature Requests

Have an idea for a new feature? Feel free to create an issue with the `enhancement` label!

## 📞 Support

Need help? Here's how to get support:
- Create an issue on GitHub
- Join our Discord server [Coming Soon]
- Contact via email: mrtroxybox@gmail.com

## ⭐ Show Your Support

If you find this plugin helpful, please consider:
- Giving it a star on GitHub
- Sharing it with others
- Contributing to its development

---

Made with ❤️ by MrTroxy