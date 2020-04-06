#ifndef TABOPTION_H
#define TABOPTION_H

#include "option.h"
#include <string>

class TabOption{
private:
	Option _opts[3];
	size_t _nb_opt;
	int getOptionIndex(const std::string &opt) const;
public:
	TabOption();
	void addOption(const Option &opt);
	void print() const;
	int getOptionId(const std::string &opt) const;
	bool optionHasArgument(const std::string &opt) const;
	Option::Type optionHasArgumentType(const std::string &opt) const;
};

#endif